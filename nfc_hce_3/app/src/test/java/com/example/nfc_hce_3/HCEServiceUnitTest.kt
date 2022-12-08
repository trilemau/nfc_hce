package com.example.nfc_hce_3

import org.junit.Assert.assertArrayEquals
import org.junit.Test


/**
 * HCE Service local unit tests, which will execute on the development machine (host).
 */
class HCEServiceUnitTest {

    @Test
    fun selectFileApdu_isCorrect() {
        val HCEService = HCEService()

        // 00 A4 04 00 08 FF 00 00 00 00 00 00 01 FF
        val apduCommand = ubyteArrayOf(
            0x00U,
            0xA4U,
            0x04U,
            0x00U,
            0x08U,
            0xFFU,
            0x00U,
            0x00U,
            0x00U,
            0x00U,
            0x00U,
            0x00U,
            0x01U,
            0xFFU
        ).toByteArray()
        val extras = null

        val response = HCEService.processCommandApdu(apduCommand, extras)

        val expected_response = ubyteArrayOf(0x90U, 0x00U).toByteArray()

        assertArrayEquals(response, expected_response)
    }

    @Test
    fun getResponseApdu_isCorrect() {
        val HCEService = HCEService()

        // 00 C0 00 00 00 FF
        val apduCommand = ubyteArrayOf(
            0x00U,
            0xC0U,
            0x00U,
            0x00U,
            0x00U,
            0xFFU
        ).toByteArray()
        val extras = null

        val response = HCEService.processCommandApdu(apduCommand, extras)

        val expected_response = ubyteArrayOf(0xA0U, 0x01U, 0x90U, 0x00U).toByteArray()

        assertArrayEquals(response, expected_response)
    }

    @Test
    fun getResponseApdu_isIncorrectDataLength() {
        val HCEService = HCEService()

        // 00 C0 00 00 01 00 FF
        val apduCommand = ubyteArrayOf(
            0x00U,
            0xC0U,
            0x00U,
            0x00U,
            0x01U,
            0x00U,
            0xFFU
        ).toByteArray()
        val extras = null

        val response = HCEService.processCommandApdu(apduCommand, extras)

        val expected_response = ubyteArrayOf(0x67U, 0x00U).toByteArray()

        assertArrayEquals(response, expected_response)
    }

    @Test
    fun apduCommand_IsNull() {
        val HCEService = HCEService()

        val apduCommand = null
        val extras = null

        val response = HCEService.processCommandApdu(apduCommand, extras)

        val expected_response = ubyteArrayOf(0x6FU, 0x00U).toByteArray()

        assertArrayEquals(response, expected_response)
    }

    @Test
    fun apduCommand_invalidLength() {
        val HCEService = HCEService()

        // 00 A4 04 00
        val apduCommand = ubyteArrayOf(
            0x00U,
            0xA4U,
            0x04U,
            0x00U,
        ).toByteArray()
        val extras = null

        val response = HCEService.processCommandApdu(apduCommand, extras)

        val expected_response = ubyteArrayOf(0x67U, 0x00U).toByteArray()

        assertArrayEquals(response, expected_response)
    }

    @Test
    fun apduCommand_unsupportedCla() {
        val HCEService = HCEService()

        // 11 A4 04 00 08 FF 00 00 00 00 00 00 01 FF
        val apduCommand = ubyteArrayOf(
            0x11U,
            0xA4U,
            0x04U,
            0x00U,
            0x08U,
            0xFFU,
            0x00U,
            0x00U,
            0x00U,
            0x00U,
            0x00U,
            0x00U,
            0x01U,
            0xFFU
        ).toByteArray()
        val extras = null

        val response = HCEService.processCommandApdu(apduCommand, extras)

        val expected_response = ubyteArrayOf(0x6EU, 0x00U).toByteArray()

        assertArrayEquals(response, expected_response)
    }

    @Test
    fun apduCommand_unsupportedIns() {
        val HCEService = HCEService()

        // 00 A5 00 00 00 FF
        val apduCommand = ubyteArrayOf(
            0x00U,
            0xA5U,
            0x00U,
            0x00U,
            0x00U,
            0xFFU
        ).toByteArray()
        val extras = null

        val response = HCEService.processCommandApdu(apduCommand, extras)

        val expected_response = ubyteArrayOf(0x6DU, 0x00U).toByteArray()

        assertArrayEquals(response, expected_response)
    }
}