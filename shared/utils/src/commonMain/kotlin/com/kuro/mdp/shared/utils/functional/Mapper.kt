package com.kuro.mdp.shared.utils.functional

interface Mapper<I, O> {
    fun map(input: I): O
}

interface ParameterizedMapper<I, O, P> {
    fun map(input: I, parameter: P): O
}
