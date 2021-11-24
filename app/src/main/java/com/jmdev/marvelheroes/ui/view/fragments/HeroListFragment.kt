package com.jmdev.marvelheroes.ui.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jmdev.marvelheroes.R
import com.jmdev.marvelheroes.data.models.characters.CharacterModel
import com.jmdev.marvelheroes.ui.adapters.CharactersAdapter
import com.jmdev.marvelheroes.databinding.HerolistFragmentBinding
import com.jmdev.marvelheroes.listener.HeroSelected
import com.jmdev.marvelheroes.ui.viewmodels.fragmentsviewmodels.HeroListFViewModel
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeroListFragment : Fragment(),HeroSelected {

    private var _binding:HerolistFragmentBinding?=null
    private val binding get() = _binding!!
    private val heroListFViewModel : HeroListFViewModel by viewModels()
    lateinit var  adapter: CharactersAdapter

    private var pageSearch:Int=0
    private var pageInit:Int=0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= HerolistFragmentBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= CharactersAdapter(this)
        heroListFViewModel.create()
        binding.herolist.rvHeroslist.layoutManager=GridLayoutManager(view.context,2)
        binding.herolist.rvHeroslist.adapter=adapter
        binding.herolist.spLayout.setOnRefreshListener(SwipyRefreshLayout.OnRefreshListener {
            pageInit+=20
            pageSearch=0
            heroListFViewModel.nextFech(pageInit)

        })
        callObservers()
    }


    fun callObservers(){

        heroListFViewModel.isLoading.observe(viewLifecycleOwner, Observer {isLoadign->
            if(isLoadign){
                binding.pbLoadherolist.visibility=View.VISIBLE
                binding.herolist.spLayout.isRefreshing=true
            }else{
                binding.pbLoadherolist.visibility=View.GONE
                binding.herolist.spLayout.isRefreshing=false
            }

        })
        heroListFViewModel.getCharactersList().observe(viewLifecycleOwner , Observer {
            adapter.setList(it)

        })
    }

    override fun onHeroSelected(characterModel: CharacterModel) {
        Log.d("HeroSelected","${characterModel.name}")
    }


}