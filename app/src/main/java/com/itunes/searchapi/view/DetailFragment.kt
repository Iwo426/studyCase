package com.itunes.searchapi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.itunes.searchapi.R
import com.itunes.searchapi.databinding.FragmentDetailBinding
import com.itunes.searchapi.response.Result
import com.itunes.searchapi.utils.dateFormat
import com.itunes.searchapi.utils.loadImage

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var data : Result
    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            arguments?.let {
                data = DetailFragmentArgs.fromBundle(it).data
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        binding?.run {
            imgArt.loadImage(data.artworkUrl100.toString())
            tvName.text = data.collectionName ?: context?.getString(R.string.name_not_found)
            tvPrice.text = getString(R.string.cost,data.collectionPrice.toString(),data.currency)
            tvDate.text = dateFormat(data.releaseDate.toString())
            tvKind.text = data.kind
            tvGenre.text = data.primaryGenreName
            tvCountry.text = data.country
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        return binding?.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}