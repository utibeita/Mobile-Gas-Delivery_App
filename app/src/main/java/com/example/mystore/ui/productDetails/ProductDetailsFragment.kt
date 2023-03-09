package com.example.mystore.ui.productDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mystore.R
import com.example.mystore.data.models.Product
import com.example.mystore.databinding.FragmentProductDetailsListDialogBinding
import com.example.mystore.ui.favourites.FavouriteViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ProductDetailsFragment(val product:Product): BottomSheetDialogFragment() {

    private lateinit var binding: FragmentProductDetailsListDialogBinding
    private lateinit var productDetailsViewModel: ProductDetailsViewModel
    private lateinit var favouriteViewModel: FavouriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        productDetailsViewModel = ViewModelProvider(this).get(ProductDetailsViewModel::class.java)
        favouriteViewModel = ViewModelProvider(this).get(FavouriteViewModel::class.java)
        binding = FragmentProductDetailsListDialogBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //load image
        Glide.with(requireContext())
            .load(product.image)
            .into(binding.productImage)

        //show details
        binding.seller.text = product.seller
        binding.price.text = "$${product.price}"
        binding.description.text = product.description
        binding.address.text = product.address

        //set listener to add to cart button
        binding.addToCart.setOnClickListener {
            //get the product id
            val id: String = product.id ?: ""

            //save to the shared preference
            productDetailsViewModel.saveToCart(product)

            //alert user that it has been added to cart
            Toast.makeText(requireContext(), "Saved to Cart", Toast.LENGTH_LONG).show()
            binding.addToCart.text = "Removed From Cart"

            //close Bottom Sheet
            this.dismiss()
        }
        //set listener to favourite button
        binding.selectAsFavourite.setOnClickListener {
            toggleFavouriteIcon()
        }

        //show appropriate favourite icon
        if (favouriteViewModel.isFavourite(product.id!!)){
            binding.selectAsFavourite.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
        }
    }

    private fun toggleFavouriteIcon() {
        //Check if product exists in favorite data store / provider
        if (favouriteViewModel.isFavourite(product.id!!)) {
            //remove item from storage
            favouriteViewModel.removeFromFavourite(product.id!!)
            //show icon as not selected
            binding.selectAsFavourite.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
        } else {
            //add item to storage
            favouriteViewModel.addToFavourite(product.id!!)
            //show icon as selected
            binding.selectAsFavourite.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
        }
    }
}