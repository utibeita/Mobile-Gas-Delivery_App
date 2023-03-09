package com.example.mystore.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mystore.databinding.FragmentFavouriteBinding
import com.example.mystore.ui.home.ProductsAdapter

class FavouriteFragment : Fragment() {
    private lateinit var fragmentFavouriteBinding: FragmentFavouriteBinding
    private lateinit var viewModel: FavouriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(FavouriteViewModel::class.java)

        fragmentFavouriteBinding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return fragmentFavouriteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getAllFavouriteProducts().observe(viewLifecycleOwner){listOfIds ->
            viewModel.getProductFromIds(listOfIds).observe(viewLifecycleOwner) { listOfProducts ->
                if(listOfProducts.isNotEmpty()){

                    fragmentFavouriteBinding.listOfFavourites.visibility = View.VISIBLE
                    fragmentFavouriteBinding.notFound.visibility = View.GONE

                    fragmentFavouriteBinding.listOfFavourites.adapter = ProductsAdapter(requireContext(),listOfProducts, childFragmentManager)
                    fragmentFavouriteBinding.listOfFavourites.layoutManager = GridLayoutManager(requireContext(),2)
                }else{
                    //Favourite is empty message
                    fragmentFavouriteBinding.listOfFavourites.visibility = View.GONE
                    fragmentFavouriteBinding.notFound.visibility = View.VISIBLE


                }
            }
        }
    }
}