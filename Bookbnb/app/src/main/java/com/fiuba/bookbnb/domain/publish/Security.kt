package com.fiuba.bookbnb.domain.publish

import java.io.Serializable

data class Security(val kit: Boolean,
                    val smokeDetector: Boolean,
                    val fireExtinguisher: Boolean,
                    val safetyInstructionsSheet: Boolean,
                    val lookBedroomDoor: Boolean) : Serializable