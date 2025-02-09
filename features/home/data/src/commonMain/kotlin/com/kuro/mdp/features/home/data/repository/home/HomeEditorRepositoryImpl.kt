package com.kuro.mdp.features.home.data.repository.home

import com.kuro.mdp.features.home.domain.mapper.editor.mapToDomain
import com.kuro.mdp.features.home.domain.mapper.editor.mapToUi
import com.kuro.mdp.features.home.domain.model.editor.EditModelHome
import com.kuro.mdp.features.home.domain.repository.home.HomeEditorRepository
import com.kuro.mdp.shared.domain.repository.feature.FeatureEditorRepository

/**
 * Created by: minhthinh.h on 2/7/2025
 *
 * Description:
 */
class HomeEditorRepositoryImpl(
    private val editorRepository: FeatureEditorRepository
) : HomeEditorRepository {

    override fun fetchEditModel(): EditModelHome {
        return editorRepository.fetchEditor().mapToUi()
    }

    override fun sendEditModel(model: EditModelHome) {
        editorRepository.saveEditor(model.mapToDomain())
    }
}