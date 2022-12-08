package com.example.nfc_hce_3

class APDUCommand {
    constructor(hexCommandApdu: String) {
        cla = hexCommandApdu.substring(0, 2)
        ins = hexCommandApdu.substring(2, 4)
        parameter1 = hexCommandApdu.substring(4, 6)
        parameter2 = hexCommandApdu.substring(6, 8)
        dataLength = hexCommandApdu.substring(8, 10).toInt(16)
        data = hexCommandApdu.substring(10, 10 + dataLength)
        responseLength = hexCommandApdu.takeLast(2).toInt(16);
    }

    val cla: String
    val ins: String
    val parameter1: String
    val parameter2: String
    val dataLength: Int
    val data: String
    val responseLength: Int
}