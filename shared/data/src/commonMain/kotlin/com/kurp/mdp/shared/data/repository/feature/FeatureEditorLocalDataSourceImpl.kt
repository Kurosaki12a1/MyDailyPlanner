package com.kurp.mdp.shared.data.repository.feature

import com.kuro.mdp.shared.domain.model.editor.Editor
import com.kuro.mdp.shared.domain.repository.feature.FeatureEditorLocalDataSource

/**
 * Created by: minhthinh.h on 2/7/2025
 *
 * Description:
 */
class FeatureEditorLocalDataSourceImpl : FeatureEditorLocalDataSource {
    private var currentValue: Editor? = null

    override fun saveEditModel(model: Editor) {
        currentValue = model
    }

    override fun fetchEditModel(): Editor {
        return checkNotNull(currentValue) { "Error transfer Editor between features" }
    }
}