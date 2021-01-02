package com.catra.ibrewer.ui.main.pages.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.catra.ibrewer.R
import com.catra.ibrewer.ui.BaseFragment
import com.catra.ibrewer.ui.main.MainProvider
import com.catra.ibrewer.utils.extensions.bind
import com.catra.ibrewer.utils.extensions.observerEvent
import com.catra.models.Beer
import kotlinx.android.synthetic.main.beer_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class BeerFragment : BaseFragment() {

    private val args: BeerFragmentArgs by navArgs()
    private val viewModel: BeerViewModel by viewModel{ parametersOf(args.beerId) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.beer_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
    }

    private fun setupUI(beer: Beer) = with(beer) {
        beerImage bind imageUrl
        collapsingToolbar bind beer.name
        val beerDescriptionList = beer.descriptionList(requireContext())

        recyclerBeerDetails.listBuilder(
            itemCount = beerDescriptionList.size,
            builder = { view, position ->
                with(view) {
                    if (position == 0) {
                        findViewById<View>(R.id.topDivider).visibility = View.GONE
                    }
                    val (label, description) = beerDescriptionList[position]
                    findViewById<TextView>(R.id.label) bind label
                    findViewById<TextView>(R.id.description) bind description
                }
            }
        )
    }

    private fun showError() {
        (activity as? MainProvider)?.displayError {
            viewModel.fetchBeer(args.beerId)
        }
    }

    private fun setupObservers() {
        viewModel.beerLiveData.observerEvent(
            this@BeerFragment,
            onSuccess = { beer ->
                setupUI(beer)
            },
            onError = {
                showError()
            },
            onLoading = {
                showLoading(hasShow = it)
            }
        )
    }
}
