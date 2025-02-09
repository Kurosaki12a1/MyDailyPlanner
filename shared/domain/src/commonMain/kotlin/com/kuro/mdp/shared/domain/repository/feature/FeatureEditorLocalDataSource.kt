package com.kuro.mdp.shared.domain.repository.feature

import com.kuro.mdp.shared.domain.model.editor.Editor

/**
 * Created by: minhthinh.h on 2/7/2025
 *
 * Description:
 */
interface FeatureEditorLocalDataSource {
    fun saveEditModel(model: Editor)

    fun fetchEditModel(): Editor
}