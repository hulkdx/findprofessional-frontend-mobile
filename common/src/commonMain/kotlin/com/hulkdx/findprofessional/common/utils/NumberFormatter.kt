package com.hulkdx.findprofessional.common.utils


object NumberFormatter {

    fun twoDigits(number: Int): String {
        // NOTE: will do the same as following
        //
        // String.format("%0$2d", number)
        //
        // String.format is not exists in native at the moment
        return if (number < 10) "0$number" else number.toString()
    }
}
