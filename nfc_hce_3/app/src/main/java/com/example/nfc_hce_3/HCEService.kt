package com.example.nfc_hce_3

import android.nfc.cardemulation.HostApduService
import android.os.Bundle
import android.util.Log

class HCEService : HostApduService() {

    // Process incoming APDU commands
    override fun processCommandApdu(commandApdu: ByteArray?, extras: Bundle?): ByteArray {
        try {
            if (commandApdu == null) {
                Log.d("PROCESS_COMMAND_APDU", "APDU COMMAND NULL")
                return Utility.hexStringToByteArray(HCEConstants.STATUS_FAILED)
            }

            val hexCommandApdu = Utility.toHex(commandApdu)
            Log.d("PROCESS_COMMAND_APDU", hexCommandApdu)

            // Check for minimum APDU length
            if (commandApdu.size < HCEConstants.MIN_APDU_LENGTH) {
                throw IllegalArgumentException("APDU command incorrect length")
                return Utility.hexStringToByteArray(HCEConstants.INCORRECT_APDU_LENGTH)
            }

            // Convert bytes to hex string
            var apduCommand = APDUCommand(hexCommandApdu)

            // Check if CLA is valid
            if (hexCommandApdu.substring(0, 2) != HCEConstants.DEFAULT_CLA) {
                Log.d("PROCESS_COMMAND_APDU", "Unsupported CLA")
                return Utility.hexStringToByteArray(HCEConstants.CLA_NOT_SUPPORTED)
            }

            // Select correct INS
            when (apduCommand.ins) {
                HCEConstants.INS_SELECT_FILE -> {
                    Log.d("PROCESS_COMMAND_APDU", "SELECT SUCCESS")
                    return Utility.hexStringToByteArray(HCEConstants.STATUS_SELECT_SUCCESS)
                }
                HCEConstants.INS_GET_RESPONSE -> {
                    Log.d("PROCESS_COMMAND_APDU", "GET RESPONSE SUCCESS")
                    return Utility.hexStringToByteArray(HCEConstants.HCE_DEVICE_UNIQUE_ID)
                }
            }
        } catch (e: Exception) {
            Log.e("PROCESS_COMMAND_APDU", e.toString())
            return Utility.hexStringToByteArray(HCEConstants.STATUS_FAILED)
        }

        return Utility.hexStringToByteArray(HCEConstants.INS_NOT_SUPPORTED)
    }

    // Lost communication between reader and Android device or another HCE Service selected
    override fun onDeactivated(reason: Int) {
        Log.d("ON_DEACTIVATED", "reason = " + reason)
    }
}
