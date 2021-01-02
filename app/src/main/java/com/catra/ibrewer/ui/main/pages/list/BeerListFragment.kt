package com.catra.ibrewer.ui.main.pages.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.catra.ibrewer.R
import com.catra.ibrewer.ui.BaseFragment
import com.catra.ibrewer.ui.main.MainProvider
import com.catra.ibrewer.utils.extensions.bind
import com.catra.ibrewer.utils.extensions.observerEvent
import kotlinx.android.synthetic.main.beer_list_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BeerListFragment : BaseFragment() {

    private val viewModel: BeerListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.beer_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupObservers()
    }

    private fun setupUI() {
        toolbar.title = getString(R.string.app_name)

        recyclerBeer.apply {
            listBuilder(
                itemCount = 0,
                refreshListener = {
                    viewModel.fetchBeers(firstPage = true)
                },
                builder = { view, position ->
                    viewModel.beerList[position].let { beer ->
                        with(view) {
                            findViewById<TextView>(R.id.name) bind beer.name
                            findViewById<ImageView>(R.id.image) bind beer.imageUrl
                            findViewById<TextView>(R.id.ibu) bind getString(R.string.ibu_label, beer.ibu)
                            this navigateToBeerFragment beer.id

                        }
                    }
                }
            )
            onPaginationListener(10) {
                viewModel.fetchBeers(firstPage = false)
            }
        }
    }

    private fun setupObservers() {
        viewModel.beerListLiveData.observerEvent(
            this@BeerListFragment,
            onSuccess = {
                recyclerBeer.submitItems(viewModel.beerList.size)
            },
            onError = {
                showError()
            },
            onLoading = {
                recyclerBeer.isRefreshLoading(it)
                showLoading(hasShow = it)
            }
        )
    }

    private fun showError() {
        (activity as? MainProvider)?.displayError {
            viewModel.fetchBeers(true)
        }
    }

    private infix fun View.navigateToBeerFragment(id: Int) {
        this.setOnClickListener {
            val action = BeerListFragmentDirections.actionBeerListFragmentToBeerFragment(id)
            findNavController().navigate(action)
        }
    }

}
