package com.cijei.dreampitch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.cijei.dreampitch.R
import com.cijei.dreampitch.adapters.PlayerSearchAdapter
import com.cijei.dreampitch.data.Player
import com.cijei.dreampitch.databinding.SelectPlayerFragmentBinding
import com.cijei.dreampitch.mock.MockPlayers
import org.w3c.dom.Text

class SelectPlayerActivity : AppCompatActivity() {

    private lateinit var binding: SelectPlayerFragmentBinding
    private lateinit var editText: EditText
    private lateinit var players: List<Player>
    private lateinit var adapter: PlayerSearchAdapter
    private lateinit var selectedPlayers: List<Player>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SelectPlayerFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        val searchEditText = binding.playerSearchEditText
        searchEditText.addTextChangedListener(textWatcher)


        players = MockPlayers().getPlayers()
        adapter = PlayerSearchAdapter(players)
        binding.playerSearchRecyclerView.adapter = adapter
        binding.playerSearchRecyclerView.layoutManager = layoutManager
    }

    val textWatcher = object: TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val searchPlayers = players.filter { player ->
                player.name.contains(p0!!, true)
            }
            adapter.setSearchData(searchPlayers)
        }

        override fun afterTextChanged(p0: Editable?) {

        }

    }
}