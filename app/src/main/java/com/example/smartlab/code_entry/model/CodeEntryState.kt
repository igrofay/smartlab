package com.example.smartlab.code_entry.model

internal data class CodeEntryState(
    val isCreatingNewCode: Boolean,
    val code: String = "",
    val codeEntryFollowingActions: CodeEntryFollowingActions = CodeEntryFollowingActions.None,
    val sizeCode: Int = 4,
)