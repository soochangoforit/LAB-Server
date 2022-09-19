package lab.reservation_server.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lab.reservation_server.dto.request.ExpireDate;
import lab.reservation_server.dto.response.DefaultDataResponse;
import lab.reservation_server.dto.response.token.TokenValue;
import lab.reservation_server.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = "Token Controller : 토큰 발급")
@Slf4j
public class TokenController {

    private final TokenService tokenService;

    /**
     * 조교로 부터 만료 날짜를 받는다.
     * 토큰을 UUID 형식으로 발급하여 DB에 저장
     * @return token
     *
     * @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
     */
    @GetMapping("/api/token")
    @ApiOperation(value="토큰 발급" , notes = "유효기간이 존재하는 토큰을 받을 수 있다.")
    public ResponseEntity<DefaultDataResponse<TokenValue>> createToken(@RequestBody @Valid ExpireDate expirationDate) {

      String token = tokenService.createToken(expirationDate);
      TokenValue tokenValue = new TokenValue(token);
      return ResponseEntity.ok(DefaultDataResponse.of(HttpStatus.CREATED,"success for token", tokenValue));
    }



}