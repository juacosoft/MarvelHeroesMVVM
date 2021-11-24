package com.jmdev.marvelheroes.ui.view


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView


import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.jmdev.marvelheroes.R
import com.jmdev.marvelheroes.databinding.ActivityMainBinding

import com.jmdev.marvelheroes.ui.view.fragments.HeroListFragment
import com.jmdev.marvelheroes.ui.view.fragments.SearchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentContainerView)as NavHostFragment
        navController=navHostFragment.navController//Navigation.findNavController(this,R.id.fragmentContainerView)
        binding.bottomNavBar.setOnItemSelectedListener {
            menuItem->
            when(menuItem.itemId){
                R.id.actionheros->{
                    navController.navigate(R.id.heroListFragment)
                    true
                }
                R.id.actioncomics->{
                    Log.d("Click","Comics")
                    navController.navigate(R.id.comicsListFragment)
                    true
                }
                R.id.actionevents->{
                    Log.d("Click","Events")
                    true
                }
                else->false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.action_menu,menu)
        val searchItem=menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.queryHint="Buscar Heroe"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val bundle =Bundle()
                bundle.putString("query",query)

                /*Navigation
                    .findNavController(this@MainActivity,R.id.fragmentContainerView)
                    .navigate(R.id.searchFragment,bundle)*/
                navController.navigate(R.id.searchFragment,bundle)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }

        })
        searchItem.setOnActionExpandListener (object : MenuItem.OnActionExpandListener{
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                //Navigation.findNavController(this@MainActivity,R.id.fragmentContainerView).navigate(R.id.heroListFragment)
                navController.navigate(R.id.heroListFragment)

                return true
            }

        })


        return super.onCreateOptionsMenu(menu)
    }


}