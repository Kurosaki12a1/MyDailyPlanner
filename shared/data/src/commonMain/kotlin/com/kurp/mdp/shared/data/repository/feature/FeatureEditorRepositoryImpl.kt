package com.kurp.mdp.shared.data.repository.feature

import com.kuro.mdp.shared.domain.model.editor.Editor
import com.kuro.mdp.shared.domain.repository.feature.FeatureEditorLocalDataSource
import com.kuro.mdp.shared.domain.repository.feature.FeatureEditorRepository

/**
 * Created by: minhthinh.h on 2/7/2025
 *
 * Description:
 */
class FeatureEditorRepositoryImpl(
    private val localDataSource: FeatureEditorLocalDataSource
) : FeatureEditorRepository {
    override fun fetchEditor(): Editor {
        return localDataSource.fetchEditModel()
    }

    override fun saveEditor(model: Editor) {
        localDataSource.saveEditModel(model)
    }
}