package com.Backend.sos.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*
import javax.crypto.spec.SecretKeySpec

@Service
class JwtService { //todo lo relacionado con el token para que se genere de manera automatica

        var  secret = "s3cr37" //llevarla a base64 para mandarla com Key a la firma de nuestro token
    fun generateToken(userDetails: UserDetails): String{
        val claims:Claims = Jwts.claims().setSubject(userDetails.username)
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis()+1000*60*24))
            .signWith(getKey(), SignatureAlgorithm.ES256)
            .compact() //para que cree el objeto y lo serialice
    }

    private fun getKey(): Key {
        //byteArrayOf(Keybytes = Decoders.BASE64)

        val encode =  Base64.getEncoder().encodeToString(secret.toByteArray())
        return SecretKeySpec(encode.toByteArray(),SignatureAlgorithm.HS256.jcaName)
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    fun extractUsername(token: String): String {
        return extractClaim(token, Claims::getSubject)
    }

    fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).body
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    private fun extractExpiration(token: String): Date {
        return extractClaim(token, Claims::getExpiration)
    }
}
