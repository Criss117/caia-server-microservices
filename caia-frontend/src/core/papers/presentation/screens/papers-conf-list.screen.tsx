import { auth } from "@/auth";
import findPapersByConfAction from "../../data/actions/find-by-conf.action";
import PapersTable from "../components/papers-table";

interface Props {
  slug: string;
}
const PapersConfListScreen = async ({ slug }: Props) => {
  const session = await auth();

  if (!session) {
    return null;
  }

  const papers = await findPapersByConfAction(session.jwt, slug);

  return <PapersTable papers={papers.data || []} inConference />;
};

export default PapersConfListScreen;
