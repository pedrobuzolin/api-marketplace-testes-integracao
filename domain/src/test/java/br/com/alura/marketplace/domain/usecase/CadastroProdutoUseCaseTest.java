package br.com.alura.marketplace.domain.usecase;

import br.com.alura.marketplace.domain.entity.Produto;
import br.com.alura.marketplace.domain.exception.BusinessException;
import br.com.alura.marketplace.domain.repository.BucketRepository;
import br.com.alura.marketplace.domain.repository.PetStoreRepository;
import br.com.alura.marketplace.domain.repository.ProdutoRepository;
import br.com.alura.marketplace.domain.repository.QueueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static br.com.alura.marketplace.domain.entity.factory.ProdutoFactory.criarProduto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;


@ExtendWith(MockitoExtension.class)
class CadastroProdutoUseCaseTest {
    @InjectMocks
    CadastroProdutoUseCase cadastroProdutoUseCase;

    @Mock
    ProdutoRepository produtoRepository;

    @Mock
    PetStoreRepository petStoreRepository;

    @Mock
    BucketRepository bucketRepository;

    @Mock
    QueueRepository queueRepository;


    @DisplayName("Quando cadastrar produto")
    @Nested
    class Cadastrar {

        @BeforeEach
        void beforeEach() {
            lenient()
                    .when(petStoreRepository.cadastrarPet(any()))
                    .thenAnswer(invocation -> {
                            Produto produto = invocation.getArgument(0);
                            setField(produto, "petStorePetId", 99L);
                            return produto;
                        });
        }

        @DisplayName("Então deve executar com sucesso")
        @Nested
        class Sucesso {
            @BeforeEach
            void beforeEach() {
                when(produtoRepository.save(any()))
                        .thenAnswer(invocation -> {
                            Produto produto = invocation.getArgument(0);
                            setField(produto, "produtoId", UUID.fromString("7625497a-0b0c-4e6e-b5d6-a07137975b49"));
                            return produto;
                        });
            }

            @DisplayName("Dado um produto com todos os campos")
            @Test
            void test1() {
                //Dado
                var produto = criarProduto().comTodosOsCampos();

                // Quando
                var atual = cadastroProdutoUseCase.cadastrar(produto);

                // Então
                assertThat(atual.getProdutoId())
                        .isEqualTo(UUID.fromString("7625497a-0b0c-4e6e-b5d6-a07137975b49"));
            }

            @DisplayName("Dado um produto com o campo status igual a ${status}")
            @ParameterizedTest
            @EnumSource(Produto.Status.class)
            void test2(Produto.Status status) {
                //Dado
                var produto = criarProduto().comTodosOsCampos();
                setField(produto, "status", status);

                // Quando
                var atual = cadastroProdutoUseCase.cadastrar(produto);

                // Entao
                assertThat(atual.getStatus())
                        .isEqualTo(status);
            }

            @DisplayName("Dado um produto com o campo status igual a ${status}")
            @ParameterizedTest
            @CsvSource(value = {
                    "AVAILABLE | (Disponível)",
                    "PENDING   | (Pendente)",
                    "SOLD      | (Vendido)",
            }, delimiterString = "|")
            void test3(Produto.Status status, String descricaoEsperada) {
                //Dado
                var produto = criarProduto().comTodosOsCampos();
                setField(produto, "status", status);

                // Quando
                var atual = cadastroProdutoUseCase.cadastrar(produto);

                // Entao
                assertThat(atual.getDescricao())
                        .endsWith(descricaoEsperada);
            }
        }

        @DisplayName("Então deve retornar erro")
        @Nested
        class Falha {
            @DisplayName("Dado um produto com o nome que começa com -")
            @Test
            void test1() {
                //Dado
                var produto = criarProduto().comTodosOsCampos();

                setField(produto, "nome", "- Nome 2");

                // Quando
                var atual = assertThrows(BusinessException.class, () -> cadastroProdutoUseCase.cadastrar(produto));

                // Então
                assertThat(atual).hasMessage("O nome não pode começar com -");
            }
        }
    }
}