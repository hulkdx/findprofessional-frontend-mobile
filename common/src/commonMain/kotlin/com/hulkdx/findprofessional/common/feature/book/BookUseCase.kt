package com.hulkdx.findprofessional.common.feature.book

import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalAvailability


class BookUseCase {

    fun isAvailabilityIncludedInTimes(
        availability: ProfessionalAvailability,
        from: Int,
        to: Int,
    ): Boolean {
        return (availability.from.toSecondOfDay() / 60) <= from &&
                (availability.to.toSecondOfDay() / 60) >= to
    }
}
