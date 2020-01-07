package com.kucingselfie.kadesubmission.util

import org.mockito.ArgumentMatchers.eq

fun <T : Any> safeEq(value: T): T = eq(value) ?: value