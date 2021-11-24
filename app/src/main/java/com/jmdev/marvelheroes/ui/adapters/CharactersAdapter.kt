package com.jmdev.marvelheroes.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jmdev.marvelheroes.R
import com.jmdev.marvelheroes.data.models.characters.CharacterModel
import com.jmdev.marvelheroes.databinding.ItemCharactersBinding
import com.jmdev.marvelheroes.domain.CharactersUseCase
import com.jmdev.marvelheroes.listener.HeroSelected
import javax.inject.Inject

class CharactersAdapter (
    private var heroSelectedListener:HeroSelected
        )
    :RecyclerView.Adapter<CharactersAdapter.BindableViewHolder>() {

    //lateinit var bindind: ItemCharactersBinding
    //private val charactersList:List<CharacterModel>? = emptyList()
    private var characters= mutableListOf<CharacterModel>()



    fun setList(_characters:List<CharacterModel>){
        this.characters.clear()
        this.characters=_characters.toMutableList()
        notifyDataSetChanged()
        Log.d("CallSetList","$characters")
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder {

        val bindind=ItemCharactersBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BindableViewHolder(bindind)

        //val rootView=LayoutInflater.from(parent.context).inflate(R.layout.item_characters,parent,false)



    }

    override fun onBindViewHolder(holder: BindableViewHolder, position: Int) {
        holder.onBind(characters[position])

    }

    override fun getItemCount(): Int {
        return characters.size
    }


    inner class BindableViewHolder(
        private var itemBinding: ItemCharactersBinding) : RecyclerView.ViewHolder(itemBinding.root){


        fun onBind(characterModel: CharacterModel){
            val imgUrl="${characterModel.thumbnailModel.path}.${characterModel.thumbnailModel.extension}"
            Log.d("ImageUrl",imgUrl)

            Glide.with(itemView)
                .load(imgUrl)
                .into(itemBinding.ivCharacterimage)

            itemBinding.tvHeroName.text=characterModel.name
            itemView.setOnClickListener { heroSelectedListener.onHeroSelected(characterModel) }
        }

    }
}