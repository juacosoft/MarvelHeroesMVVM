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
import com.jmdev.marvelheroes.data.models.comics.ComicsModel
import com.jmdev.marvelheroes.databinding.ComicslistFragmentBinding

import com.jmdev.marvelheroes.listener.ComicSelected
import com.jmdev.marvelheroes.ui.adapters.ComicsAdapter
import com.jmdev.marvelheroes.ui.viewmodels.fragmentsviewmodels.ComicsListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ComicsListFragment() : Fragment(),ComicSelected {
    private var _binding: ComicslistFragmentBinding?=null
    private val binding get() = _binding!!

    private val comicsListViewModel : ComicsListViewModel by viewModels()
    lateinit var  adapter: ComicsAdapter

    private var pageSearch:Int=0
    private var pageInit:Int=0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= ComicslistFragmentBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= ComicsAdapter(this)
        comicsListViewModel.fetchComics(pageSearch)
        binding.comicsList.rvHeroslist.layoutManager=GridLayoutManager(view.context,2)
        binding.comicsList.rvHeroslist.adapter=adapter
        binding.comicsList.spLayout.setOnRefreshListener{
            pageInit+=20
            pageSearch=0
            comicsListViewModel.fetchComics(pageInit)
        }
        callObservers()
    }

    fun callObservers(){
        comicsListViewModel.isLoading.observe(viewLifecycleOwner, Observer {isLoading->
            if(isLoading){
                binding.pbLoadherolist.visibility=View.VISIBLE
                binding.comicsList.spLayout.isRefreshing=true
            }else{
                binding.pbLoadherolist.visibility=View.GONE
                binding.comicsList.spLayout.isRefreshing=false
            }
        })
        comicsListViewModel.getComicsList().observe(viewLifecycleOwner, Observer { comics->
            adapter.setList(comics)
        })
    }

    override fun onComicSelected(comicsModel: ComicsModel) {
        Log.d("ComicSelected","$comicsModel")
    }
}