package com.catra.ibrewer.screens.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.catra.ibrewer.R
import com.catra.ibrewer.utils.extensions.observerEvent
import com.catra.ibrewer.utils.extensions.setImage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.beer_list_itemview.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()
        setupData()
        setupObservers()
    }

    private fun setupUI() {
        recyclerBeer.apply {
            listBuilder(
                itemCount = 0,
                refreshListener = {
                    viewModel.fetchBeers(1)
                },
                builder = { view, position ->
                    viewModel.beerList[position].let { beer ->
                        with(view) {
                            name.text = beer.name
                            image setImage beer.imageUrl
                            ibu.text = getString(R.string.ibu_label, beer.ibu)
                        }
                    }
                }
            )
//            onPaginationListener(10) {
//
//            }
        }
    }

    private fun setupObservers() {
        viewModel.beerListLiveData.observerEvent(
            this@MainActivity,
            onSuccess = {
                recyclerBeer.submitItems(viewModel.beerList.size)
            },
            onError = {

            },
            onLoading = {
                recyclerBeer.isRefreshLoading(it)
            }
        )
    }

    private fun setupData() {
        viewModel.fetchBeers(1)
    }
}