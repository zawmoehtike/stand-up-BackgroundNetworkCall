package com.example.stand_up.data.networkvos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/*
{
        "shop_statuses": {
            "submitted": {
                "name": "Submitted"
            },
            "approved": {
                "name": "Approved"
            },
            "rejected": {
                "name": "Rejected"
            }
        },
        "services": [
            {
                "id": 7,
                "category": "Grooming",
                "pet_type": "dog",
                "name": "Dog - Grooming",
                "description": "<p><center><string>This is a nice HTML for Dog - Grooming</string></center></p><p>Bathing and clipping dogs to conform to a variety of breed-specific standard styles. Detangling and removing matted hair. Drying the coat. Checking for parasites and other skin conditions.</p>",
                "icon_url": "http://162.0.211.67/asset/dog_grooming.png"
            },
            {
                "id": 8,
                "category": "Grooming",
                "pet_type": "dog",
                "name": "Dog - VIP Grooming",
                "description": "<p><center><string>This is a nice HTML for Dog - VIP Grooming</string></center></p><p>Bath/Brush, Custom Shampoo, Blowdry, Sanitary Trim, Feet and Face Trimming, Nail Trimming, Gland expression and Ear cleaning. This service is for dogs 6 months or younger.</p>",
                "icon_url": "http://162.0.211.67/asset/dog_vip_grooming.png"
            }
        ],
        "countries": [
            {
                "id": 1,
                "name": "Malaysia",
                "iso_code": "MY",
                "phone_code": "+60"
            }
        ],
        "provinces": [
            {
                "id": 14,
                "name": "Wilayah Persekutuan - Kuala Lumpur",
                "cities": [
                    {
                        "id": 112,
                        "name": "Kuala Lumpur",
                        "areas": [
                            {
                                "id": 1,
                                "name": "Hartamas"
                            }
                        ]
                    },
                    {
                        "id": 519,
                        "name": "Mont Kiara",
                        "areas": [
                            {
                                "id": 3,
                                "name": "Solaris Mont Kiara"
                            }
                        ]
                    }
                ]
            }
        ],
        "breeds": {
            "dog": [
                {
                    "id": 1,
                    "name": "Others"
                },
                {
                    "id": 2,
                    "name": "Affenpinscher"
                },
                {
                    "id": 3,
                    "name": "Afgan Hound"
                }
            ],
            "cat": [
                {
                    "id": 175,
                    "name": "Others"
                },
                {
                    "id": 176,
                    "name": "Abyssinian"
                },
                {
                    "id": 177,
                    "name": "American Bobtail"
                }
            ],
            "breed_ids_needing_custom": [
                1,
                175
            ]
        },
        "default_country_id": 1,
        "default_province_id": 14,
        "default_city_id": 112,
        "allowed_mobile_prefix": "601"
    }
 */
class ResourcesVO(
    @SerializedName("shop_statuses")
    @Expose
    var shopStatusVO: ShopStatusVO? = null,
    @SerializedName("services")
    @Expose
    var services: ArrayList<ServiceVO> = ArrayList(),
    @SerializedName("countries")
    @Expose
    var countries: ArrayList<CountryVO> = ArrayList(),
    @SerializedName("provinces")
    @Expose
    var provinceVO: ArrayList<ProvinceVO> = ArrayList(),
    @SerializedName("breeds")
    @Expose
    var breeds: BreedVO? = null,
    @SerializedName("default_country_id")
    @Expose
    var defaultCountryId: String = "",
    @SerializedName("default_province_id")
    @Expose
    var defaultProvinceId: String = "",
    @SerializedName("default_city_id")
    @Expose
    var defaultCityId: String = "",
    @SerializedName("allowed_mobile_prefix")
    @Expose
    var allowedMobilePrefix: String = ""
)