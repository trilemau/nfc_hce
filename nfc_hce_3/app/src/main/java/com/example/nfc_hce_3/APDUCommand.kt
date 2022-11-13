package com.example.nfc_hce_3

class APDUCommand {
    constructor(hexCommandApdu: String) {
        cla = hexCommandApdu.substring(0, 2)
        ins = hexCommandApdu.substring(2, 4)
        parameter1 = hexCommandApdu.substring(4, 6)
        parameter2 = hexCommandApdu.substring(6, 8)
        dataLength = hexCommandApdu.substring(8, 10)
        data = hexCommandApdu.substring(10, 10 + dataLength.toInt(16))
        responseLength = hexCommandApdu.takeLast(2);
    }

    val cla: String
    val ins: String
    val parameter1: String
    val parameter2: String
    val dataLength: String
    val data: String
    val responseLength: String
}