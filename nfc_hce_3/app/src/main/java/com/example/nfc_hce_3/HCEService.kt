package com.example.nfc_hce_3

import android.nfc.cardemulation.HostApduService
import android.os.Bundle
import android.util.Log

class HCEService : HostApduService() {

    object HCEConstants {
        const val TAG = "Host Card Emulator"
        const val STATUS_SELECT_SUCCESS = "9000"
        const val STATUS_FAILED = "6F00"
        const val CLA_NOT_SUPPORTED = "6E00"
        const val INS_NOT_SUPPORTED = "6D00"
        const val AID = "FF3F12C1583A69D28C4082412A90AC00"
        const val SELECT_INS = "A4"
        const val GET_INS = "C0"
        const val DEFAULT_CLA = "00"
        const val MIN_APDU_LENGTH = 12
    }

    // Everytime a card reader sends an APDU command that is filtered by our manifest filter
    override fun processCommandApdu(commandApdu: ByteArray?, extras: Bundle?): ByteArray {
        // TODO: APDU to class
        // https://github.com/City-of-Helsinki/android-hce/blob/master/app/src/main/java/fi/hel/helsinkihcedemo/Apdu.kt

        if (commandApdu == null) {
            return Utility.hexStringToByteArray(HCEConstants.STATUS_FAILED)
        }

        val hexCommandApdu = Utility.toHex(commandApdu)

        Log.d("PROCESS_COMMAND_APDU", commandApdu.toString())
        Log.d("PROCESS_COMMAND_APDU", hexCommandApdu)


        if (hexCommandApdu.length < HCEConstants.MIN_APDU_LENGTH) {
            return Utility.hexStringToByteArray(HCEConstants.STATUS_FAILED)
        }

        if (hexCommandApdu.substring(0, 2) != HCEConstants.DEFAULT_CLA) {
            return Utility.hexStringToByteArray(HCEConstants.CLA_NOT_SUPPORTED)
        }

        if (hexCommandApdu.substring(2, 4) == HCEConstants.SELECT_INS) {

//        if (hexCommandApdu.substring(10, 24) == HCEConstants.AID)  {
//            return Utility.hexStringToByteArray(HCEConstants.STATUS_SUCCESS)
//        } else {
//            return Utility.hexStringToByteArray(HCEConstants.STATUS_FAILED)
//        }

            Log.d("PROCESS_COMMAND_APDU", "SELECT SUCCESS")

//            return Utility.hexStringToByteArray(HCEConstants.STATUS_SELECT_SUCCESS)
            return Utility.hexStringToByteArray("AAAAAAAA")
        }

        if (hexCommandApdu.substring(2, 4) == HCEConstants.GET_INS) {
            Log.d("PROCESS_COMMAND_APDU", "GET SUCCESS")

            return Utility.hexStringToByteArray("1234567890ABCDEEF")
        }

        return Utility.hexStringToByteArray(HCEConstants.INS_NOT_SUPPORTED)
    }

    // Different AID has been selected or lost NFC connection
    override fun onDeactivated(reason: Int) {
        Log.d(HCEConstants.TAG, "Deactivated: " + reason)
    }
}