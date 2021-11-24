package com.jmdev.marvelheroes.ui.adapters


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jmdev.marvelheroes.data.models.comics.ComicsModel
import com.jmdev.marvelheroes.databinding.ItemCharactersBinding
import com.jmdev.marvelheroes.listener.ComicSelected

class ComicsAdapter(
    private var comicSelected: ComicSelected
):RecyclerView.Adapter<ComicsAdapter.BindableViewHolder> (){


    private var comics= mutableListOf<ComicsModel>()

    fun setList(_comics:List<ComicsModel>){
        this.comics.clear()
        this.comics=_comics.toMutableList()
        notifyDataSetChanged()
        Log.d("CallSetList","$comics")
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder {
        val binding=ItemCharactersBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BindableViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindableViewHolder, position: Int) {
        holder.onBind(comics[position])
    }

    override fun getItemCount(): Int {
        return comics.size
    }

    inner class BindableViewHolder(
        private var itemBinding:ItemCharactersBinding
    ):RecyclerView.ViewHolder(itemBinding.root){


        fun onBind(comic: ComicsModel){
            Log.d("AdapterComic",comic.title)
            val imgUrl="${comic.thumbnail.path}.${comic.thumbnail.extension}"
            itemBinding.tvHeroName.text=comic.title
            Glide.with(itemView)
                .load(imgUrl)
                .into(itemBinding.ivCharacterimage)
            itemView.setOnClickListener {
                comicSelected.onComicSelected(comic)
            }

        }
    }
}