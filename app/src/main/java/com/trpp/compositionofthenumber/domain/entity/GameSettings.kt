package com.trpp.compositionofthenumber.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class GameSettings (
    val maxSumValue: Int,
    val minCountOfRightAnswers: Int,
    val minPercentOfRightAnswers: Int,
    val gameTimeInSeconds: Int,
):Parcelable