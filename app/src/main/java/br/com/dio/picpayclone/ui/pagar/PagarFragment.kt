package br.com.dio.picpayclone.ui.pagar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.dio.picpayclone.Componentes
import br.com.dio.picpayclone.ComponentesViewModel
import br.com.dio.picpayclone.R
import br.com.dio.picpayclone.data.Usuario
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_pagar.*
import kotlinx.android.synthetic.main.fragment_pagar.recyclerView
import org.koin.android.viewmodel.ext.android.viewModel

class PagarFragment : Fragment() {

    private val componentesViewModel: ComponentesViewModel by viewModel()
    private val pagarViewModel: PagarViewModel by viewModel()
    private val controlador by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pagar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componentesViewModel.temComponentes = Componentes(bottomNavigation = true)
        observarContatos()
        observarLoading()
    }

    private fun observarLoading() {
        pagarViewModel.onLoading.observe(viewLifecycleOwner, Observer { onLoading ->
            if (onLoading) {
                progressBarPagar.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                progressBarPagar.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        })
    }

    private fun observarContatos() {
        pagarViewModel.contatos.observe(viewLifecycleOwner, Observer {
            configurarRecyclerView(it)
        })
    }

    private fun configurarRecyclerView(usuarios: List<Usuario>) {
        recyclerView.adapter = PagarAdapter(usuarios) {
            val direcao = PagarFragmentDirections.actionNavigationPagarToNavigationTransferencia(it)
            controlador.navigate(direcao)
        }
    }
}
