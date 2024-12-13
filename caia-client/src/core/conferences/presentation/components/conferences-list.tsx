import { auth } from "@/auth";
import ConferenceCard from "./conference-card";
import findConferencesAction from "../../data/actions/find-conferences";

interface Props {
  onDiscover?: boolean;
}

const ConferencesList = async ({ onDiscover }: Props) => {
  const session = await auth();

  if (!session) {
    return null;
  }

  const jwt = onDiscover ? null : session.jwt;

  const { data } = await findConferencesAction(jwt);

  return (
    <div className="grid md:grid-cols-2 xl:grid-cols-3 gap-5 gap-y-10 my-10">
      {data?.map((conference, index) => (
        <ConferenceCard conference={conference} key={index} onDashboard />
      ))}
    </div>
  );
};

export default ConferencesList;
