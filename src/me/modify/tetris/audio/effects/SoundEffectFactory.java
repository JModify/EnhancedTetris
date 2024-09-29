package me.modify.tetris.audio.effects;

public class SoundEffectFactory {

    public static SoundEffect createSoundEffect(Effect type) {
        switch (type) {
            case MOVE -> {
                return new MoveSoundEffect();
            }

            case GAME_OVER -> {
                return new GameOverSoundEffect();
            }

            case ROW_CLEAR -> {
                return new RowClearSoundEffect();
            }

            case LEVEL_UP -> {
                return new LevelUpSoundEffect();
            }
        }

        return new ErrorSoundEffect();
    }
}
