package com.uid4oe.advice

import org.springframework.stereotype.Service

@Service
class AdviceBusiness(val repository: AdviceRepository) {

    fun getAdvice(id: String) = repository.findById(id)

    fun createUpdateAdvice(advice: AdviceModel) = repository.save(advice)

}

