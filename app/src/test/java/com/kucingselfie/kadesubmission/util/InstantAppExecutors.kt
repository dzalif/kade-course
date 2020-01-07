package com.kucingselfie.kadesubmission.util

import com.kucingselfie.kadesubmission.util.AppExecutors
import java.util.concurrent.Executor

class InstantAppExecutors : AppExecutors(
    instant,
    instant,
    instant
) {
    companion object {
        private val instant = Executor { it.run() }
    }
}