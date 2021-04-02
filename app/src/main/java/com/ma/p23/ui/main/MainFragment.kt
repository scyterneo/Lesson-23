package com.ma.p23.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ma.p23.R
import com.ma.p23.adapter.UserAdapter
import com.ma.p23.data.User
import java.util.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var usersList: RecyclerView
    private val usersAdapter: UserAdapter = UserAdapter()
    private lateinit var addUserBtn: View
    private lateinit var userName: EditText

    private val users: MutableList<User> = generateUsers() as MutableList<User>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        usersList = view.findViewById(R.id.users_list)
        addUserBtn = view.findViewById(R.id.add_btn)
        userName = view.findViewById(R.id.name_input)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        usersList.layoutManager = LinearLayoutManager(requireContext())
        usersAdapter.setUsers(users)
        usersList.adapter = usersAdapter

        addUserBtn.setOnClickListener {
            val user = User(userName.text.toString(), android.R.drawable.ic_menu_add)
            usersAdapter.addUser(user)
        }
    }

    private fun generateUsers(): List<User> {
        val users: MutableList<User> = mutableListOf()
        val randomSize = Random().nextInt(2)
        val names = listOf("Vasya", "Petya", "Sasha")
        val icons = listOf(
            R.drawable.ic_launcher_background,
            android.R.drawable.ic_input_add,
            android.R.drawable.ic_menu_send
        )
        for (i in 0..randomSize) {
            val user = User(names[i % names.size], icons[i % icons.size])
            users.add(user)
        }
        return users
    }

}