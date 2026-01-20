import java.io.IOException;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class cliclick {
    public static void main(String[] args) throws IOException {
    Terminal terminal = TerminalBuilder.builder().build();

    try {
        // Different mouse tracking modes

        // 1. Basic mouse tracking (clicks only)
        terminal.trackMouse(Terminal.MouseTracking.Button);

        // 2. Extended mouse tracking (clicks and movement)
        // This is terminal-dependent and may require specific escape sequences
        terminal.writer().write("\033[?1000;1002;1006;1015h");

        // 3. Any event tracking (clicks, movement, and position reports)
        // This is terminal-dependent and may require specific escape sequences
        terminal.writer().write("\033[?1000;1003;1006;1015h");

        terminal.flush();

        System.out.println("Enhanced mouse tracking enabled.");
        System.out.println("Try clicking, moving, and scrolling the mouse.");
        System.out.println("Press Enter to exit.");

        // Wait for Enter key
        while (terminal.reader().read() != '\n') {
            // Process events
        }
    } finally {
        // Disable all mouse tracking modes
        terminal.trackMouse(Terminal.MouseTracking.Off);
        terminal.writer().write("\033[?1000;1002;1003;1006;1015l");
        terminal.flush();

        terminal.close();
    }
}
}
