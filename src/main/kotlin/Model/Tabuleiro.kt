package Model

enum class TabuleiroEvento { VITORIA, DERROTA }

class Tabuleiro(val qtdeLinhas: Int, val qtdeColunas: Int, private val qtdeMinas: Int) {
    private val campos = ArrayList<ArrayList<Campo>>()
    private val callbacks = ArrayList<(TabuleiroEvento) -> Unit>()

    init {
        gerarCampos()
        associarVizinhos()
        sortearMinas()
    }
    private fun gerarCampos() {
        for (linha in 0 until qtdeLinhas) {
            campos.add(ArrayList())
            for (coluna in 0 until qtdeColunas) {
                val novoCampo = Campo(linha, coluna)
                novoCampo.onEvento(this::verificarDerrotaOuVitoria)
                campos[linha].add(novoCampo)
            }
        }
    }
    private fun associarVizinhos() {
        forEachCampo { associarVizinhos(it) }
    }
}