package org.example.data;

import junit.framework.TestCase;
import org.example.flow.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

//@ExtendWith(MockitoExtension.class)
public class PlayerTest extends TestCase {
//  @Mock
  private Player player;

  @Override
  @Before
  public void setUp() throws Exception {
    this.player = new Player(5);
  }

  @Test
  public void testPay() {
  }

  public void testEat() {
    Player player = new Player(5);
    Packet dish = Packet.builder().calories(1).isPoison(true).build();
    Packet stolenDish = Packet.builder().calories(2).build();
//    ArgumentCaptor<Map> requestBodyCaptor = ArgumentCaptor.forClass(Map.class);
//    verify(documentService, times(1))
//            .executeInitDocumentRequest(requestBodyCaptor.capture(), any());
    player = player.eat(dish, stolenDish);
    assertTrue(player.getCalories() == 2);
  }

  public void testTestEat() {
  }

  public void testIsAlive() {
  }
}
