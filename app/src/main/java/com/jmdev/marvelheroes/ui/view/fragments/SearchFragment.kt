package com.jmdev.marvelheroes.ui.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.jmdev.marvelheroes.data.models.characters.CharacterModel
import com.jmdev.marvelheroes.databinding.HerolistFragmentBinding
import com.jmdev.marvelheroes.databinding.HerosearchlistFragmentBinding
import com.jmdev.marvelheroes.listener.HeroSelected
import com.jmdev.marvelheroes.ui.adapters.CharactersAdapter
import com.jmdev.marvelheroes.ui.viewmodels.fragmentsviewmodels.HeroListFViewModel
import com.jmdev.marvelheroes.ui.viewmodels.fragmentsviewmodels.SearchHeroViewModel
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), HeroSelected {
    private var _binding: HerosearchlistFragmentBinding?=null
    private val binding get() = _binding!!
    private val searchHeroViewModel : SearchHeroViewModel by viewModels()

    lateinit var  adapter: CharactersAdapter
    private var page:Int=0
    private var query=""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= HerosearchlistFragmentBinding.inflate(inflater,container,false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args=arguments

        if(args!=null) {
            query = args.getString("query").toString()
            searchHeroViewModel.searchFech(page,query)
        }
        adapter= CharactersAdapter(this)
        binding.herolist.rvHeroslist.layoutManager= GridLayoutManager(view.context,2)
        binding.herolist.rvHeroslist.adapter=adapter
        binding.herolist.spLayout.setOnRefreshListener(SwipyRefreshLayout.OnRefreshListener {
            page+=20
            searchHeroViewModel.searchFech(page,query)

        })

        callObserve()

    }

    private fun callObserve(){
        searchHeroViewModel.isLoading.observe(viewLifecycleOwner, Observer {isLoading->
            if(isLoading){
                binding.pbLoadherolist.visibility=View.VISIBLE
                binding.herolist.spLayout.isRefreshing=true
            }else{
                binding.pbLoadherolist.visibility=View.GONE
                binding.herolist.spLayout.isRefreshing=false
            }
        })
        searchHeroViewModel.getSearchListHero().observe(viewLifecycleOwner, Observer {characters->
            adapter.setList(characters)
        })
    }

    override fun onHeroSelected(characterModel: CharacterModel) {
        Log.d("HeroSelected",characterModel.name)
    }


}