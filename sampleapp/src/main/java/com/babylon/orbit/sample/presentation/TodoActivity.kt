package com.babylon.orbit.sample.presentation

import android.os.Bundle
import com.babylon.orbit.launcher.view.OrbitActivity
import com.babylon.orbit.sample.R
import com.babylon.orbit.sample.presentation.mock.TodoScreenStateMocks
import com.babylon.orbit.sample.presentation.renderer.TodoRenderer
import kotlinx.android.synthetic.main.todo_view.*
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class TodoActivity : OrbitActivity<TodoScreenState, Unit>() {

    private val renderer by lazy { TodoRenderer(this, viewModel) }

    override val viewModel by stateViewModel<TodoViewModel>()

    override fun connect() = viewModel.connect(this, ::render)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.todo_view)

        renderer.prepare(
            recyclerView = recycler_view,
            retryButton = retry_button
        )
    }

    override fun render(state: TodoScreenState) {
        super.render(state)

        renderer.render(
            state = state,
            recyclerView = recycler_view,
            progressContainer = progress_container,
            errorContainer = error_container
        )
    }

    override fun provideMocks() = TodoScreenStateMocks.mocks
}
