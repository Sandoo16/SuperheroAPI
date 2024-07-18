package com.example.superheroapp

import android.os.Bundle
import com.bumptech.glide.Glide
import androidx.appcompat.app.AppCompatActivity
import com.example.superheroapp.model.Superhero
import com.example.superheroapp.databinding.ActivityMainBinding
import com.example.superheroapp.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchSuperheroData()
    }

    private fun fetchSuperheroData() {
        ApiConfig.apiService.getSuperhero().enqueue(object : Callback<Superhero> {
            override fun onResponse(call: Call<Superhero>, response: Response<Superhero>) {
                val superhero = response.body()
                if (superhero != null) {
                    displaySuperhero(superhero)
                }
            }

            override fun onFailure(call: Call<Superhero>, t: Throwable) {
            }
        })
    }

    private fun displaySuperhero(superhero: Superhero) {
        binding.tvName.text = superhero.name
        binding.tvFullName.text = "Full Name: ${superhero.biography.`fullname`}"
        binding.tvPowerstats.text = """
            Intelligence: ${superhero.powerstats.intelligence}
            Strength: ${superhero.powerstats.strength}
            Speed: ${superhero.powerstats.speed}
            Durability: ${superhero.powerstats.durability}
            Power: ${superhero.powerstats.power}
            Combat: ${superhero.powerstats.combat}
        """.trimIndent()
        binding.tvBiography.text = """
            
            Place of Birth: ${superhero.biography.`placeofbirth`}
            First Appearance: ${superhero.biography.`firstappearance`}
            Publisher: ${superhero.biography.publisher}
            Alignment: ${superhero.biography.alignment}
        """.trimIndent()


        Glide.with(this)
            .load(superhero.image.url)
            .into(binding.ivImage)
    }
}