import { create } from "zustand";
import { ConferencesDto } from "../../data/dto/conference.dto";

interface State {
  conference: ConferencesDto | null;

  setConference: (conference: ConferencesDto) => void;
}

const useCurrentConferenceState = create<State>()((set) => ({
  conference: null,
  setConference: (conference: ConferencesDto) => set({ conference }),
}));

export default useCurrentConferenceState;
