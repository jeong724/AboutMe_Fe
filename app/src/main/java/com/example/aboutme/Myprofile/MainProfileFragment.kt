package com.example.aboutme.Myprofile

import com.example.aboutme.RetrofitMyprofile.RetrofitClient
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.aboutme.R
import com.example.aboutme.RetrofitMyprofileData.FrontFeature
import com.example.aboutme.RetrofitMyprofileData.MainProfileData
import com.example.aboutme.databinding.FragmentMainprofileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainProfileFragment : Fragment() {

    lateinit var binding: FragmentMainprofileBinding
    private val multiList = mutableListOf<MultiProfileData>() // 전역 변수로 multiList 선언
    private lateinit var vpadapter : MainProfileVPAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainprofileBinding.inflate(inflater, container, false)

        //binding.mainProfileVp.pageMargin = resources.getDimensionPixelOffset(R.dimen.viewpager_page_margin)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        initViewPager()

        binding.mainProfileVp.offscreenPageLimit=3

        /*var transform = CompositePageTransformer()
        transform.addTransformer(MarginPageTransformer(8))

        transform.addTransformer(ViewPager2.PageTransformer{ view: View, fl: Float ->
            var v = 1-Math.abs(fl)
            view.scaleY = 0.8f + v * 0.2f
        })

        binding.mainProfileVp.setPageTransformer(transform)*/

    }

    private fun initViewPager() {

        vpadapter = MainProfileVPAdapter()

        binding.mainProfileVp.adapter = vpadapter


        //binding.mainProfileVp.setPageTransformer(MarginPageTransformer(resources.getDimensionPixelOffset(R.dimen.viewpager_page_margin)))


        RetrofitClient.mainProfile.getData().enqueue(object : Callback<MainProfileData> {
            // 서버 통신 실패 시의 작업
            override fun onFailure(call: Call<MainProfileData>, t: Throwable) {
                Log.e("실패", t.toString())
            }


            override fun onResponse(call: Call<MainProfileData>, response: Response<MainProfileData>) {
                val repos: MainProfileData? = response.body()
                if (repos != null) {
                    val frontFeatures: List<FrontFeature>? = repos.result.myprofiles?.flatMap { profile ->
                        profile.frontFeatures
                    }
                    val totalMyProfile = repos.getTotalMyProfile()

                    if (frontFeatures != null) {
                        multiList.clear()
                        for (profile in repos.result.myprofiles) {
                            val frontFeatures = profile.frontFeatures
                                if (frontFeatures.size > 1) {

                                    if (profile.profileImage.type == "USER_IMAGE"){
                                        if (profile.profileImage.profileImageUrl != null) {
                                            multiList.add(MultiProfileData(
                                                R.drawable.prof_avater1,
                                                frontFeatures[0].value,
                                                frontFeatures[1].value
                                            ))
                                        }
                                    } else if(profile.profileImage.type == "DEFAULT"){
                                        multiList.add(MultiProfileData(
                                            R.drawable.profiledefault,
                                            frontFeatures[0].value,
                                            frontFeatures[1].value

                                        ))} else if (profile.profileImage.type == "CHARACTER"){
                                        if (profile.profileImage.characterType == "1") {
                                            multiList.add(MultiProfileData(
                                                R.drawable.prof_avater1,
                                                frontFeatures[0].value,
                                                frontFeatures[1].value

                                            ))
                                        }
                                        if (profile.profileImage.characterType == "2") {
                                            multiList.add(MultiProfileData(
                                                R.drawable.prof_avater2,
                                                frontFeatures[0].value,
                                                frontFeatures[1].value

                                            ))
                                        }
                                        if (profile.profileImage.characterType == "3") {
                                            multiList.add(MultiProfileData(
                                                R.drawable.prof_avater3,
                                                frontFeatures[0].value,
                                                frontFeatures[1].value

                                            ))
                                        }
                                        if (profile.profileImage.characterType == "4") {
                                            multiList.add(MultiProfileData(
                                                R.drawable.prof_avater4,
                                                frontFeatures[0].value,
                                                frontFeatures[1].value

                                            ))
                                        }
                                        if (profile.profileImage.characterType == "5") {
                                            multiList.add(MultiProfileData(
                                                R.drawable.prof_avater5,
                                                frontFeatures[0].value,
                                                frontFeatures[1].value

                                            ))
                                        }
                                        if (profile.profileImage.characterType == "6") {
                                            multiList.add(MultiProfileData(
                                                R.drawable.prof_avater6,
                                                frontFeatures[0].value,
                                                frontFeatures[1].value

                                            ))
                                        }
                                        if (profile.profileImage.characterType == "7") {
                                            multiList.add(MultiProfileData(
                                                R.drawable.prof_avater7,
                                                frontFeatures[0].value,
                                                frontFeatures[1].value

                                            ))
                                        }
                                        if (profile.profileImage.characterType == "8") {
                                            multiList.add(MultiProfileData(
                                                R.drawable.prof_avater8,
                                                frontFeatures[0].value,
                                                frontFeatures[1].value

                                            ))
                                        }
                                        if (profile.profileImage.characterType == "9") {
                                            multiList.add(MultiProfileData(
                                                R.drawable.prof_avater9,
                                                frontFeatures[0].value,
                                                frontFeatures[1].value

                                            ))
                                        }
                                    }else{
                                    multiList.add(MultiProfileData(
                                            R.drawable.frontprofile_basic,
                                            frontFeatures[0].value,
                                            frontFeatures[1].value

                                        )
                                    )}
                                }else {

                                    if (profile.profileImage.type == "USER_IMAGE") {
                                        if (profile.profileImage.profileImageUrl != null) {
                                            multiList.add(
                                                MultiProfileData(
                                                    R.drawable.prof_avater1,
                                                    frontFeatures[0].value,
                                                    ""
                                                )
                                            )
                                        }
                                    } else if (profile.profileImage.type == "DEFAULT") {
                                        multiList.add(
                                            MultiProfileData(
                                                R.drawable.profiledefault,
                                                frontFeatures[0].value,
                                                ""

                                            )
                                        )
                                    } else if (profile.profileImage.type == "CHARACTER") {
                                        if (profile.profileImage.characterType == "1") {
                                            multiList.add(
                                                MultiProfileData(
                                                    R.drawable.prof_avater1,
                                                    frontFeatures[0].value,
                                                    ""
                                                )
                                            )
                                        }
                                        if (profile.profileImage.characterType == "2") {
                                            multiList.add(
                                                MultiProfileData(
                                                    R.drawable.prof_avater2,
                                                    frontFeatures[0].value,
                                                    ""

                                                )
                                            )
                                        }
                                        if (profile.profileImage.characterType == "3") {
                                            multiList.add(
                                                MultiProfileData(
                                                    R.drawable.prof_avater3,
                                                    frontFeatures[0].value,
                                                    ""

                                                )
                                            )
                                        }
                                        if (profile.profileImage.characterType == "4") {
                                            multiList.add(
                                                MultiProfileData(
                                                    R.drawable.prof_avater4,
                                                    frontFeatures[0].value,
                                                    ""

                                                )
                                            )
                                        }
                                        if (profile.profileImage.characterType == "5") {
                                            multiList.add(
                                                MultiProfileData(
                                                    R.drawable.prof_avater5,
                                                    frontFeatures[0].value,
                                                    ""

                                                )
                                            )
                                        }
                                        if (profile.profileImage.characterType == "6") {
                                            multiList.add(
                                                MultiProfileData(
                                                    R.drawable.prof_avater6,
                                                    frontFeatures[0].value,
                                                    ""

                                                )
                                            )
                                        }
                                        if (profile.profileImage.characterType == "7") {
                                            multiList.add(
                                                MultiProfileData(
                                                    R.drawable.prof_avater7,
                                                    frontFeatures[0].value,
                                                    ""

                                                )
                                            )
                                        }
                                        if (profile.profileImage.characterType == "8") {
                                            multiList.add(
                                                MultiProfileData(
                                                    R.drawable.prof_avater8,
                                                    frontFeatures[0].value,
                                                    ""

                                                )
                                            )
                                        }
                                        if (profile.profileImage.characterType == "9") {
                                            multiList.add(
                                                MultiProfileData(
                                                    R.drawable.prof_avater9,
                                                    frontFeatures[0].value,
                                                    ""

                                                )
                                            )
                                        }
                                    } else {
                                        multiList.add(
                                            MultiProfileData(
                                                R.drawable.frontprofile_basic,
                                                frontFeatures[0].value,
                                                ""

                                            )
                                        )
                                    }
                                }

                            }

                        // 어댑터에 업데이트된 multiList를 제출합니다.
                        vpadapter.submitList(multiList)
                        Log.d("성공티비","success")
                        Log.d("FrontFeature List", multiList.toString())
                    } else {
                        Log.e("실패", "front_features 데이터가 null입니다.")
                    }
                } else {
                    Log.e("실패", "응답 데이터가 null입니다.")
                    Log.e("Response", "${response.code()}")
                }
                binding.mainProfileVp.postDelayed({
                    binding.mainProfileVp.setCurrentItem(0, false)
                }, 50)
            }
        })

    }



}