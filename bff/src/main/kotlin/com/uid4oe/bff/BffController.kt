package com.uid4oe.bff

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
@RequestMapping("/api/")
class BffController(val business: BffBusiness) {

    @GetMapping("users/")
    fun getUsers() = ResponseEntity.ok(BffResponse(business.getUsers()))

    @GetMapping("users/{id}")
    fun getUserDetails(@PathVariable id: String) = ResponseEntity.ok(BffResponse(business.getUserDetails(id)))

    @PostMapping("users/")
    fun createUser(@RequestBody req: User) = ResponseEntity.ok(BffResponse(business.createUser(req)))

    @PutMapping("users/{id}")
    fun updateUser(@PathVariable id: String, @RequestBody req: User): ResponseEntity<BffResponse<String>> {
        return ResponseEntity.ok(BffResponse(business.updateUser(id,req)))
    }

    @PutMapping("advices/")
    fun updateUserAdvice(@RequestBody req: Advice): ResponseEntity<BffResponse<String>> {
        return ResponseEntity.ok(BffResponse(business.updateAdvice(req)))
    }
}

