package org.acme.gqlreproducer;

import io.smallrye.graphql.api.Subscription;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.acme.gqlreproducer.model.Ping;
import org.acme.gqlreproducer.model.Pong;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;
import java.time.Duration;

@GraphQLApi
public class PingPongResource {

    Multi<Pong> sharedPublisher;

    Multi<Pong> tickerMulti = Multi.createFrom().items(1,2,3,4,5,6,7,8,9,10)
            .onItem()
            .call(i ->
                    Uni.createFrom()
                            .item(i)
                            .onItem()
                            .delayIt()
                            .by(Duration.ofSeconds(1)))
            .map(Pong::new);

    @Query
    public Pong getPong() {
        return null;
    }

    @Subscription
    public Multi<Pong> pong(Ping p) {
        return Multi.createFrom().items(1,2,3,4,5,6,7,8,9,10)
                .onItem()
                .call(i ->
                        Uni.createFrom()
                                .item(i)
                                .onItem()
                                .delayIt()
                                .by(Duration.ofSeconds(1)))
                .map(Pong::new);
    }

    @Subscription
    public Multi<Pong> pongTogether(Ping p) {
        if(sharedPublisher == null) {
            sharedPublisher = tickerMulti;
        }
        return sharedPublisher;
    }
}