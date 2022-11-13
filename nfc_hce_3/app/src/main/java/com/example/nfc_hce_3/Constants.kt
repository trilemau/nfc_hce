package com.example.nfc_hce_3

object HCEConstants {
    const val AID = "FF00000000000001"
    const val MIN_APDU_LENGTH = 5
    const val INCORRECT_APDU_LENGTH = "6700"

    const val DEFAULT_CLA = "00"

    const val INS_SELECT_FILE = "A4"
    const val INS_GET_RESPONSE = "C0"

    const val STATUS_SUCCESS = "9000"
    const val STATUS_FAILED = "6F00"
    const val CLA_NOT_SUPPORTED = "6E00"
    const val INS_NOT_SUPPORTED = "6D00"

    const val HCE_DEVICE_UNIQUE_ID = "A001"
}