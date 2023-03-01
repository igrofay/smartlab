package com.example.smartlab.code_entry.model

internal sealed class CodeEntryEvent {
    class EnteringCodeCharacter(val char: Char) : CodeEntryEvent()
    object DeleteCodeCharacter: CodeEntryEvent()
    object SkipCreatingNewCode : CodeEntryEvent()
}