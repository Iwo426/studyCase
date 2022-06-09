package com.itunes.searchapi.view

import android.os.Bundle
import android.os.Parcelable
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.itunes.searchapi.adapter.SearchAdapter
import com.itunes.searchapi.databinding.FragmentSearchBinding
import com.itunes.searchapi.response.Result
import com.itunes.searchapi.utils.SearchType
import com.itunes.searchapi.utils.enableButtons
import com.itunes.searchapi.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class SearchFragment : Fragment(), SearchAdapter.ItemInterface {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    @OptIn(ExperimentalCoroutinesApi::class)
    private val viewModelSearch: SearchViewModel by viewModels()
    lateinit var adapter: SearchAdapter
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding
    var entitiy = SearchType.Movie.entity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        if (savedInstanceState != null) {
            binding?.searchList?.layoutManager?.onRestoreInstanceState(
                savedInstanceState.getParcelable("data"));
        }
        adapter = SearchAdapter(context)
        adapter.interfaceListener(this)
        binding?.run {
           searchList.layoutManager = GridLayoutManager(context, 2)
           searchList.adapter = adapter
            txtSearch.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {

                }

                override fun beforeTextChanged(
                    s: CharSequence, start: Int,
                    count: Int, after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence, start: Int,
                    before: Int, count: Int
                ) {
                    if (s.length > 2) {
                        setUpList(s.toString())
                    }else if (s.isEmpty()){
                        adapter.submitData(lifecycle, PagingData.empty())
                    }
                }
            })

            val btnList: List<View> = listOf(btnMovie,btnMusic,btnEbook,btnPodcast)

           btnMovie.setOnClickListener {
               txtSearch.setText("")
               enableButtons(btnList)
               resetData(btnMovie,SearchType.Movie.entity)
            }

           btnMusic.setOnClickListener {
               txtSearch.setText("")
               enableButtons(btnList)
               resetData(btnMusic,SearchType.Music.entity)
            }
          btnEbook.setOnClickListener {
              txtSearch.setText("")
              enableButtons(btnList)
              resetData(btnEbook,SearchType.Ebook.entity)
            }

          btnPodcast.setOnClickListener {
              txtSearch.setText("")
              enableButtons(btnList)
              resetData(btnPodcast,SearchType.Podcast.entity)
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
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    @ExperimentalCoroutinesApi
    private fun setUpList(query: String) {
        lifecycleScope.launch {
            viewModelSearch.searchMovie(0, query, entitiy)
                .collectLatest { pagedData ->
                    adapter.submitData(pagedData)
                }
        }
    }
    override fun onSaveInstanceState(@NonNull outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("data", binding?.searchList?.layoutManager?.onSaveInstanceState())
    }

    override fun sendData(data: Result) {
        val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(data)
        findNavController().navigate(action)
    }
    private fun resetData(btn : View, entityString: String){
        btn.isEnabled = false
        entitiy = entityString
    }
}