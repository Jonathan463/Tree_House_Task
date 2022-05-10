package com.treehouseapp.service.serviceImpl;


import com.treehouseapp.dto.PayStackRequest;
import com.treehouseapp.payload.PayStackResponse;
import com.treehouseapp.dto.VerifyTransactionDto;
import com.treehouseapp.model.enums.PaymentMethod;
import com.treehouseapp.model.enums.TransactionType;
import com.treehouseapp.model.users.User;
import com.treehouseapp.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
/*@RequiredArgsConstructor*/
public class PaystackServiceImpl {
    private final TransactionServiceImpl transactionService;
    private final UserRepository userRepository;
    private final WebClient.Builder webClient;
    private final ObjectMapper objectMapper;
    private final String secret;

    public PaystackServiceImpl(TransactionServiceImpl transactionService, UserRepository userRepository,
                               WebClient.Builder webClient, ObjectMapper objectMapper,@Value("${paystack.Secret}") String secret) {
        this.transactionService = transactionService;
        this.userRepository = userRepository;
        this.webClient = webClient;
        this.objectMapper = objectMapper;
        this.secret = secret;
    }


    public Object initializePaystackTransaction (PayStackRequest payStackRequestDto,
                                                 long userId, TransactionType type ){
        String transactionReference = transactionService.getTransactionRefence(userId, type, PaymentMethod.PAYSTACK);

        if (!(transactionReference.contains("CHOMPPSTK") || transactionReference.contains("CHOMPFND"))){
            return new ResponseEntity<>(transactionReference, HttpStatus.BAD_REQUEST );
        }

        User user = userRepository.findById(userId).get();
        String[] channels = {"card", "bank"};
        payStackRequestDto.setAmount(payStackRequestDto.getAmount()*100);
        payStackRequestDto.setReference(transactionReference);
        payStackRequestDto.setEmail(user.getEmail());
        payStackRequestDto.setChannels(channels);
        payStackRequestDto.setCallback_url("https://chompfood-9bdbd.web.app/");

        return webClient.build().post().uri("https://api.paystack.co/transaction/initialize").
                header("Authorization", "Bearer " + secret ).bodyValue(payStackRequestDto)
                .retrieve().bodyToMono(Object.class).block();
    }

    public PayStackResponse verifyPaystackTransaction(VerifyTransactionDto verifyTransactionDto) throws JsonProcessingException {
        String paystackObject =  webClient.build().get().
                uri("https://api.paystack.co/transaction/verify/" + verifyTransactionDto.getTransactionReference()).
                header("Authorization", "Bearer " + secret)
                .retrieve().bodyToMono(String.class).block();

        PayStackResponse payStackDto = objectMapper.readValue( paystackObject, PayStackResponse.class);


        return payStackDto;
    }




}
