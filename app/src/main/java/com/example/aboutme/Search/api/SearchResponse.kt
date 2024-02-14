package com.example.aboutme.Search.api

class SearchResponse {
    //상대방 마이프로필 내 보관함에 추가하기 - 바디
    data class RequestStoreProf (
        var profile_serial_numbers : List<Int>
            )

    //상대방 마이프로필 내 보관함에 추가하기 - 헤더
    data class ResponseStoreProf(
        var isSuccess : Boolean,
        var code : String,
        var message : String
    )


    //스페이스 검색
    data class ResponseSearchSpace (
        var isSuccess: Boolean,
        var code: String,
        var message: String,
        var result : SpaceList
            )
    data class SpaceList(
        var spaceId : Long,
        var nickname : String,
        var characterType : Int,
        var roomType : Int
    )

    //스페이스 보관함에 추가
    data class ResponseSpaceStorage (
        var isSuccess: Boolean,
        var code: String,
        var message: String,
        var result : SpaceId
    )
    data class SpaceId (
        var spaceId : Long
            )

    //마이프로필 검색
    data class ResponseSearchProf (
        var isSuccess: Boolean,
        var code: String,
        var message: String,
        var result : Profile
            )
    data class Profile (
        var profile_id : Long,
        var serial_number : Int,
        var profile_image : Image,
        var front_features  : List<Front>
            )
    data class Image (
        var type : String,
        var characterType : Int?,
        var profile_image_url : String?
            )
    data class Front (
        var key : String,
        var value : String,
        var feature_id : Long
            )
}