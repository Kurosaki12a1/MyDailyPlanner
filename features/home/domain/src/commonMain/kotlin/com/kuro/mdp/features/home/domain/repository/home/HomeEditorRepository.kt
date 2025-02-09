package com.kuro.mdp.features.home.domain.repository.home

import com.kuro.mdp.features.home.domain.model.editor.EditModelHome

/**
 * Created by: minhthinh.h on 2/7/2025
 *
 * Description:
 */
interface HomeEditorRepository {
    fun fetchEditModel(): EditModelHome

    fun sendEditModel(model: EditModelHome)
}