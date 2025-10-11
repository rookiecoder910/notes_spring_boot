package com.plcoding.spring_boot_crash_courses.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter(
    private val jwtService: JwtService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            val token = authHeader.removePrefix("Bearer ").trim()
            try {
                if (jwtService.validateAccessToken(token)) {
                    val userId = jwtService.getUserIdFromToken(token)
                    val auth = UsernamePasswordAuthenticationToken(userId, null, emptyList())
                    SecurityContextHolder.getContext().authentication = auth
                }
            } catch (e: Exception) {
                println("JWT validation failed: ${e.message}")
            }
        }

        filterChain.doFilter(request, response)
    }
}
